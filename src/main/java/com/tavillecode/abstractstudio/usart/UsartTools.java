package com.tavillecode.abstractstudio.usart;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/6/9 12:50
 */
public class UsartTools {
    public static SerialPort serialPort=null;

    //检测可使用串口
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ArrayList<String> uartPortUseAbleFind() {
        //获取当前所有可用串口
        //由CommPortIdentifier类提供方法
        Enumeration<CommPortIdentifier> portList=CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList=new ArrayList();
        //添加并返回ArrayList
        while(portList.hasMoreElements()) {
            String portName=portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    //连接指定串口并调制波特率
    public static void portParameterOpen(String portName, int baudrate) {
        try {  //通过端口名识别串口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            //打开端口并设置端口名字 serialPort和超时时间 2000ms
            CommPort commPort=portIdentifier.open(portName,1000);
            //进一步判断comm端口是否是串口 instanceof
            if(commPort instanceof SerialPort) {
                System.out.println("This COM port is a serial port! The serial port name is: " + portName);
                //进一步强制类型转换
                serialPort=(SerialPort)commPort;
                //设置baudrate 此处需要注意:波特率只能允许是int型
                //8位数据位
                //1位停止位
                //无奇偶校验
                serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //串口配制完成 log
                System.out.println("Serial port parameter setting completed, baudrate is "+baudrate+",Data bits 8 bits, stop bit 1 bit, no parity check");
            } else {    //不是串口
                System.out.println("The COM port is not a serial port, please check the device!");
                //将com端口设置为null 默认是null不需要操作
            }

        } catch (NoSuchPortException | UnsupportedCommOperationException | PortInUseException e) {
            e.printStackTrace();
        }

    }

    /*
     * 串口数据发送以及数据传输作为一个类
     * 该类做主要实现对数据包的传输至下单板机
     */
    public static void sendData(String data) throws IOException {
        serialPort.getOutputStream().write(data.getBytes());
    }

    /*
     * 上位机接收数据
     * 串口对象seriesPort
     * 接收数据buffer
     * 返回一个byte数组
     */
    public  static  byte[] uartReceiveDatafromSingleChipMachine(SerialPort serialPort) {
        byte[] receiveDataPackage=null;
        InputStream in=null;
        try {
            in=serialPort.getInputStream();
            // 获取data buffer数据长度
            int bufferLength=in.available();
            while(bufferLength!=0) {
                receiveDataPackage=new byte[bufferLength];
                in.read(receiveDataPackage);
                bufferLength=in.available();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveDataPackage;
    }

    //发送
    public static void action(String parameter) throws Exception {
        // 第一次操作时打开串口
        if (serialPort == null) {
            portParameterOpen("COM5", 9600);
        }

        // 要发送的数据
        String dataSend = parameter;

        sendData(dataSend);
        System.out.println("The data which is sent to STM32: " + dataSend.replaceAll("\r\n","").replaceAll("@",""));

        // 休眠500ms，等待单片机反应
        //Thread.sleep(500);

        // 从单片机接收到的数据
            /*
            byte[] dat = uartReceiveDatafromSingleChipMachine(serialPort);
            if(dat != null && dat.length > 0) {
                String dataReceive = new String(dat, "GB2312");
                System.out.println((i++) + ". 从串口接收的数据：" + dataReceive);
            } else {
                System.out.println("接收到的数据为空！");
            }
            */
        //}
    }

    /*
     * 关闭串口
     */
    public static void closePort() {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }
}
