/**
  * ʱ��Ƭ��ת��
  * @Author vs
  * @Description //get(ʱ��Ƭ��С���������С���������)set(ʱ��Ƭ��С)
  * @Date 2019��11��24��17��36��
  * @Param TODO
  * @return
  **/

package com.os.homework;

import java.util.Queue;

public class timearound {
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

    public void RUN()
    {
        //����
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
