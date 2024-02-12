package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {

    private static int N = 5;

    public static void main (String[] args) {

        DisplayStub display;
        SensorStub sensor;

        RPCClient displayclient,sensorclient;

        System.out.println("Controller starting ...");

        // create RPC clients for the system
        displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
        sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);

        // setup stop methods in the RPC middleware
        RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
        RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);

        // TODO - START

        // create local display and sensor stub objects
        display = new DisplayStub(displayclient);
        sensor = new SensorStub(sensorclient);

        // connect to sensor and display RPC servers - using the RPCClients
        displayclient.connect();
        sensorclient.connect();
        // read value from sensor using RPC and write to display using RPC
        int x=1;
        while (x<6){
            Integer send =sensor.read();
            String stringSend= send.toString();
            display.write(stringSend);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            x++;
        }

        //if (true)	throw new UnsupportedOperationException(TODO.method());


        stopdisplay.stop();
        stopsensor.stop();

        displayclient.disconnect();
        sensorclient.disconnect();

        System.out.println("Controller stopping ...");

    }
}