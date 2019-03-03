package code;

import java.lang.*;

public class QueueSimulator {
    public enum Event {ARRIVAL, DEPARTURE}


    private double currTime; //current time progression during simulation
    private double arrivalRate; //rate of which packets are coming into buffer queue
    private double serviceTime; //service time is constant
    private double timeForNextArrival; //time at which the next packet will arrive into buffer queue
    private double timeForNextDeparture; //time at which the next packet will depart from buffer queue
    private double totalSimTime; //total simulation time
    LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
    LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
    private Event e;

    public double getRandTime(double arrivalRate) {
        double num, time1, max = 1, min = 0, randNUM;
        randNUM = Math.random();
        time1 = (-1 / arrivalRate) * (Math.log(1 - randNUM));
        //System.out.println(time1);
        return time1;
    }

    public QueueSimulator(double aR, double servT, double simT) {
        arrivalRate = aR;
        serviceTime = servT;
        totalSimTime = simT;
        timeForNextArrival = getRandTime(arrivalRate);
        timeForNextDeparture = timeForNextArrival + serviceTime;
    }

    public double calcAverageWaitingTime() {
        int size = eventQueue.size();
        double totalTime = 0;
        while (!eventQueue.isEmpty()) {
            Data d = eventQueue.dequeue();
            totalTime += d.getDepartureTime() - d.getArrivalTime();
        }
        return totalTime / size;

    }

    public double runSimulation() {
        Data d;
        while (currTime < totalSimTime) {
            e = (timeForNextArrival < timeForNextDeparture) ? Event.ARRIVAL : Event.DEPARTURE;
            if (buffer.isEmpty()) e = Event.ARRIVAL;
            switch (e) {
                case ARRIVAL:
                    currTime = timeForNextArrival;
                    d = new Data();
                    d.setArrivalTime(currTime);
                    buffer.enqueue(d);
                    timeForNextArrival = currTime + getRandTime(arrivalRate);
                    break;

                case DEPARTURE:
                    currTime = timeForNextDeparture;
                    d = buffer.dequeue();
                    d.setDepartureTime(currTime);
                    eventQueue.enqueue(d);
                    if (!buffer.isEmpty())
                        timeForNextDeparture += serviceTime;
                    else
                        timeForNextDeparture = timeForNextArrival + serviceTime;

            }
        }
        return calcAverageWaitingTime();
    }
}
