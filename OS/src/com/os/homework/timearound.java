/**
  * ʱ��Ƭ��ת��
  * @Author vs
  * @Description //get(ʱ��Ƭ��С���������С���������)set(ʱ��Ƭ��С)
  * @Date 2019��11��24��17��36��
  * @Param TODO
  * @return
  **/

package com.os.homework;

import com.os.homework.UI.ProcessRuntime;
import com.os.homework.UI.Source;
import com.os.homework.UI.UtilInfos;

import java.sql.SQLOutput;
import java.util.*;
import java.text.SimpleDateFormat;


public class timearound {
    private int nowtime= 0 ;
    IO io = new IO();
    boolean ioflag = false;
    static PV[] pv = new PV[2];
    static {
        pv[0] = new PV();
        pv[1] = new PV();
    }
    process process1 = new process();
    process process2 = new process();
    process process3 = new process();
    process pc = new process();
    private int time_size;            //ʱ��Ƭ��С
    private ArrayList<process> ready_queue = new ArrayList<process>(); //��������
    private ArrayList<process> pvblock_queue = new ArrayList<process>(); //��������

    public void setTime_size(int time_size) {
        this.time_size = time_size;
        System.out.println("ʱ��Ƭ��С��"+time_size);
    }

    public process getProcess1() {
        return process1;
    }

    public process getProcess2() {
        return process2;
    }

    public process getProcess3() {
        return process3;
    }

    public void setProcess1(process process1) {
        this.process1 = process1;
    }

    public void setProcess2(process process2) {
        this.process2 = process2;
    }

    public void setProcess3(process process3) {
        this.process3 = process3;
    }

    public int getTime_size() {
        return time_size;
    }

    public ArrayList<process> getReady_queue() {
        return ready_queue;
    }

    public ArrayList<process> getBlock_queue() {
        return pvblock_queue;
    }

    public void init_pro(){
        //��ʼ����������,�ӽ����ȡ����

    }
    public void arrive(){
        if (process1.getArrive_time()==nowtime){
            ready_queue.add(process1);
        }
        if (process2.getArrive_time()==nowtime){
            ready_queue.add(process2);
        }
        if (process3.getArrive_time()==nowtime){
            ready_queue.add(process3);
        }
    }
    public void io_op(){
        if(!io.ifempty()){
            UtilInfos.UpdateIO(io.getIO_queue().get(0));
            ioflag = io.run();
            if (ioflag){
                process p = io.out_IO_queue();
                ready_queue.add(p);
            }
        }
        else
        {
            UtilInfos.UpdateIO(new process(""));
        }
    }
    public void RUN(){
        //����

        int cacheTime = 1000,delay = 2000;
        init_pro();

        //��λʱ��һ��ѭ��
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int whichpvsource = 0 ;
                nowtime++;
                arrive(); //��������������
                io_op();  //io���� io��ɽ����������

                ProcessRuntime.UpdateTime();
                UtilInfos.Updateinfo(null, nowtime);

                if(!ready_queue.isEmpty()) {
                    pc = ready_queue.get(0);
                    UtilInfos.Updateinfo(pc, nowtime);
                    //�Ӿ���������ѡ��һ����cpu , ��ɡ�io��pv��Դ��ռ�õ��뿪
                    pc.run();

                    if (pc.iffinish()) {
                        ready_queue.remove(0);
                        System.out.println("һ�����ǽ���");
                    } else if (pc.ifio()) {
                        io.in_IO_queue(pc);
                        ready_queue.remove(0);
                    } else if (pc.ifp()) {
                        p();
                    } else if (pc.ifv()) {
                        v();
                    } else {
                        ready_queue.set(0, pc);
                    }


                    if (!ready_queue.isEmpty()&&nowtime % time_size == 0) {   //ʱ��Ƭ���� ����
                        process temp = ready_queue.get(0);
                        ready_queue.remove(0);
                        ready_queue.add(temp);
                    }
                }
            }
        }, delay, cacheTime);
    }

    public void R2B_queue(process pro)
    {
        //�������С�����������

        pvblock_queue.add(pro);
        ready_queue.remove(pro);
    }

    public void B2R_queue(process pro)
    {
        //�������С�����������
        ready_queue.add(pro);
        pvblock_queue.remove(pro);
    }
    public void p(){
        int  whichpvsource = pc.whichp();
        if(whichpvsource!=3){
            if(!pv[whichpvsource-1].ifoccupy)  //��Դû�б�ռ��
            {
                pv[whichpvsource - 1].P();
                Source.UpdateSouce(whichpvsource, pc);
            }
            else{
                //��Դ��ռ�� ����pv����
                R2B_queue(pc);
            }
        }
        else{
            if(!pv[0].ifoccupy&&!pv[1].ifoccupy) { //��Դû�б�ռ��
                pv[1].P();
                pv[0].P();
            }
            else{
                //��Դ��ռ�� ����pv����
                R2B_queue(pc);
            }
        }


    }
    public void v(){
        //�ͷ���Դ
        int  whichv = pc.whichv();
        if(whichv == 1 || whichv == 2)
            pv[whichv-1].V();
        else if(whichv == 3){
            pv[0].V();
            pv[1].V();
        }
        //�ж�pv��������һ������������
        int which_source;
        for(process i :pvblock_queue){
            which_source = i.whichp();
            if (which_source == 3&&pv[0].ifoccupy==false&&pv[1].ifoccupy==false){
                B2R_queue(i);
            }else {
                if(!pv[which_source-1].ifoccupy){
                    B2R_queue(i);
                }
            }
        }
    }
}
