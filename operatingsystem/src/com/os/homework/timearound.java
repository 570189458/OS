/**
  * ʱ��Ƭ��ת��
  * @Author vs
  * @Description //get(ʱ��Ƭ��С���������С���������)set(ʱ��Ƭ��С)
  * @Date 2019��11��24��17��36��
  * @Param TODO
  * @return
  **/

package com.os.homework;

import java.sql.SQLOutput;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;


public class timearound {
    private int nowtime= 0 ;
    process process1 = new process();
    process process2 = new process();
    process process3 = new process();
    private float time_size;            //ʱ��Ƭ��С
    private Queue<process> ready_queue; //��������
    private Queue<process> block_queue; //��������

    public void setTime_size(float time_size) {
        this.time_size = time_size;
    }

    public float getTime_size() {
        return time_size;
    }

    public Queue<process> getReady_queue() {
        return ready_queue;
    }

    public Queue<process> getBlock_queue() {
        return block_queue;
    }

    public void init_pro(){
        //��ʼ����������,�ӽ����ȡ����

    }
    public void RUN()
    {
        //����
        int cacheTime = 1000,delay = 2000;
        init_pro();
        int whichpro = 0;

        //��λʱ��һ��ѭ��
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��

                if(nowtime%time_size ==0 ) {   //ʱ��Ƭ���� ����
                    //�ж�˭����������


                }
                nowtime++;

                //�Ӿ���������ѡ��һ����cpu , ��ɡ�io��pv��Դ��ռ�õ��뿪
                if(whichpro == 1){
                    process1.run();

                    if(process1.iffinish()) {
                        //����������
                    }
                    if(process1.ifio()){
                        //����io����
                    }
                    if (process1.ifpv()){
                        //����pv����
                    }
                }else if(whichpro == 2){
                    process1.run();
                    if(process1.iffinish()) {
                        //����������
                    }
                    if(process1.ifio()){
                        //����io����
                    }
                    if (process1.ifpv()){
                        //����pv����
                    }
                }else if(whichpro == 3){
                    process1.run();
                    if(process1.iffinish()) {
                        //����������
                    }
                    if(process1.ifio()){
                        //����io����
                    }
                    if(process1.ifpv()){
                        //����pv����
                    }
                }
                //���¾�������
            }
        }, delay, cacheTime);


    }

    public void R2B_queue(process pro)
    {
        //�������С�����������
    }

    public void B2R_queue(process pro)
    {
        //�������С�����������
    }
}
