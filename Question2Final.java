import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.Scanner;

import java.util.Random;

public class Question2Final{
    public class Reservation implements Runnable{
        public int totalSeatsAvailable;
        
        public void run() {
            // Initializing the current thread 
            BookingThread pt = (BookingThread)Thread.currentThread();
            // tickets check the booked tickets
            boolean ticketsBooked = this.bookTickets(pt.getSeatsNeeded(), pt.getName());
            // validate whether the tickets booked are not
            if(ticketsBooked==true){
                // Display Tickets booked message if tickets booking successful
                    System.out.println("Congratulation, ."+Thread.currentThread().getName()+" The number of seats requested ("+pt.getSeatsNeeded()+")  are BOOKED");
                }
            else{
                // Display below message if ticket booking failed
                System.out.println("My apologies Mr."+Thread.currentThread().getName()+" ..The number of seats requested("+pt.getSeatsNeeded()+")  are not available");
                }
            }
        // called synchronized method for booking tickets
        public synchronized boolean bookTickets(int seats, String name){
            System.out.println("Welcome to HappyTheatre "+Thread.currentThread().getName()+" Number of Tickets Available are:"+this.getTotalSeatsAvailable());
            
            if (seats>this.getTotalSeatsAvailable()) {
            return false; 
            } else 
            {
                totalSeatsAvailable = seats; // reduce the booked tickets from avaiable seats
                return true; // return true
                }
            }
        // method to return available seats
        private int getTotalSeatsAvailable() {
        return totalSeatsAvailable;
        }
    
    }
    public class BookingThread extends Thread{
        // Declare variable of type integer
        private int seatsNeeded;
        
        // Declare constructor of class
        public BookingThread(int seats, Runnable target, String name,int totalSeatsAvailable) {
            super(target,name); // calling the master constructor
            if(seats>0 && seats <= 3){
                this.seatsNeeded = seats; // set the value given by user
            }
            else{
                System.out.println("Seats booked must be in range of 1-3. Exiting Program");
                System.exit();
            }
            
            
        }
    
        //function return required seats from customer
        public int getSeatsNeeded() {
            return seatsNeeded; // Returns the value of seats booked
        }
    
    }
    private final static Random RANDOMISER = new Random();

            private  static int generateRandomNumber(int from, int to){
            return RANDOMISER.nextInt((to+1)-from)+from;
            }

    public synchronized boolean addTicket(int addSeats) {
        if((remainingSeats>=addSeats) &&(addSeats>0)) {
            numberOfTicketsBooked+=addSeats;
            System.out.println(Thread.currentThread().getName()+" Booking confirmed!!");
            remainingSeats -=addSeats;
            return true;
        }else {
            System.out.println(Thread.currentThread().getName()+" Sorry! unable to book ticket at this time");
            return false;
        }
            
    }
    /*Similarly, multiple cancellations will be made even if all the tickets are available for booking*/
    public synchronized boolean cancelTicket(int tickets) {
        if((tickets>0) && (maxCapacity>=tickets) && (numberOfTicketsBooked>=tickets) && (tickets<=remainingSeats)){
            remainingSeats+=tickets;
            System.out.println(Thread.currentThread().getName()+" Booking cancelled");
            return true;
        }else {
            System.out.println(Thread.currentThread().getName()+" Invalid! cancellation cannot be processed!");
            return false;
        }

    }

    public void printAllSeats(){
        for(int i = 0 ; i < numOfRows ; i++){
            for(int j = 0 ; j < numOfColumns ; j++){
                Seat currentSeat = seats.get(i).get(j);
                if(currentSeat.isSeatFree()){
                    System.out.print("E: ");
                }
                else{
                    System.out.print("T:"+currentSeat.getBookedBy()+" ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws InterruptedException {
            Reservation reserve = new Reservation(); 
            int numOfRows = 0;
            int numOfColumns = 0;
            int numberOfSeats = 0;
            int numOfUsers = 0;
            int totalRowAndColumns = 0;
            int modulusNumber = 0;
            int greaterNumberRows = 0;
            int greaterNumberColumns = 0;
            // int numberOfSeats = 0;
            Scanner myObj0 = new Scanner(System.in);
            Scanner myObj = new Scanner(System.in);
            Scanner myObj2 = new Scanner(System.in);
            Scanner myObj3 = new Scanner(System.in);
            Scanner myObj4 = new Scanner(System.in);
            System.out.println("Enter the number of Seats Available (Range from 100-200 only)");
            if(myObj0.hasNextInt()) {
                numberOfSeats = myObj0.nextInt();
                if(numberOfSeats >= 100 && numberOfSeats <= 200){
                    
                    myObj0.close();
                }
                else{
                    System.out.println("The required number of Seats Available must be in Range of 100-200 only). Exiting program.");
                    System.exit(0);
                }
            }
            else {
                System.out.println("The required seats must be an integer number. Exiting program.");
                System.exit(0);
            }
              
            System.out.println("Enter the number of Seats Available (Range from 100-200 only)");
            if(myObj.hasNextInt()) {
                numberOfSeats = myObj.nextInt();
                if(numberOfSeats >= 100 && numberOfSeats <= 200){
                    
                    myObj.close();
                }
                else{
                    System.out.println("The required number of Seats Available must be in Range of 100-200 only). Exiting program.");
                    System.exit(0);
                }
            }
            else {
                System.out.println("The required Seats must be an integer number. Exiting program.");
                System.exit(0);
            }

            System.out.println("Enter the number of Rows Available (Rows and Columns Multiplied must not exceed Total Number of Seats)");
            if(myObj2.hasNextInt()) {
                numOfRows = myObj2.nextInt();
                    myObj2.close();
            }else {
                System.out.println("The required Rows must be an integer number. Exiting program.");
                System.exit(0);
            }

            System.out.println("Enter the number of Columns Available(Rows and Columns Multiplied must not exceed Total Number of Seats) ");
            if(myObj3.hasNextInt()) {
                numOfColumns = myObj2.nextInt();
                myObj3.close();
            }else {
                System.out.println("The required Rows must be an integer number. Exiting program.");
                System.exit(0);
            }
            System.out.println("Enter the number of Users that want to book tickets");
            if(myObj4.hasNextInt()) {
                numOfUsers = myObj4.nextInt();
                myObj4.close();
            }else {
                System.out.println("The required Rows must be an integer number. Exiting program.");
                System.exit(0);
            }

              totalRowAndColumns = numOfRows * numOfColumns;
              modulusNumber = numberOfSeats % totalRowAndColumns;
              if(totalRowAndColumns > numberOfSeats){
                System.out.println("The Total Number of Seats By Column And Rows Exceed seat number. Exiting program.");
              }else{
                if(numOfRows > numOfColumns){
                    greaterNumberRows = numOfRows;
                    if(numberOfSeats != totalRowAndColumns){
                        numOfRows += modulusNumber;
                      }
                  }else{
                    greaterNumberColumns = numOfColumns;
                    if(numberOfSeats != totalRowAndColumns){
                        numOfColumns += modulusNumber;
                      }
                  }
              }
        


                System.out.println("Welcome to the ticket booking counter!!!");

                Scanner in = new Scanner(System.in);
                int input=in.nextInt();
                int newNumberOfSeats;
                int sleepTimer = generateRandomNumber(500, 1500);
               // for(int i=1; i<=4; i++){
                for(int threadIndex = 0; threadIndex < numOfUsers; threadIndex++){
                    if(threadIndex == 0){
                        String name;
                int tickets;
                System.out.println("Enter your name");
                name=in.nextLine();
                in.nextLine();
                System.out.println("Enter number of seats between");
                 tickets=in.nextInt();
                 if(tickets>0 && tickets <= 3){
                    in.nextLine();
                 }else{
                     System.out.println("Please insert a seat number between 1-3");
                 }
                BookingThread pt1 = new PassengerThread(tickets,reserve ,name, numberOfSeats);
                pt1.start();
                Thread.pt1.sleep(sleepTimer); 
                threadIndex ++;
                System.out.println("Do you want to continue?\n");
                  int input2=in.nextInt();
                  if(input2==0)
                  break;
                    }else{

                BookingThread pt2 = new PassengerThread(tickets,reserve ,name, numberOfSeats);
                pt2.start();
                Thread.pt2.sleep(sleepTimer); 
                reserve.totalSeatsAvailable = numberOfSeats;
                String name;
                int tickets;
                System.out.println("Enter your name");
                name=in.nextLine();
                in.nextLine();
                System.out.println("Enter number of seats between");
                 tickets=in.nextInt();
                 if(tickets>0 && tickets <= 3){
                    in.nextLine();
                 }else{
                     System.out.println("Please insert a seat number between 1-3");
                 }
                newNumberOfSeats = reserve.totalSeatsAvailable;
                customerlist.add(new BookingThread(tickets,reserve ,name, newNumberOfSeats));    
                threadIndex ++;
                System.out.println("Do you want to continue?\n");
                  int input2=in.nextInt();
                  if(input2==0)
                  break;
                }
	     }	
			

	}
}

