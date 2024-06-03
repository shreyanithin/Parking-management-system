import java.util.Scanner;
class Car{
   private String licensePlate;

   public Car(String licensePlate) {
       this.licensePlate = licensePlate;
   }

   public String getLicensePlate() {
       return licensePlate;
   }

   public String toString(){
       return "licensePlate='" + licensePlate + "'" ;
   }
}

class Parkingspot{
   private int spotId;
   private Car car;

   public Parkingspot(int spotId){
       this.spotId=spotId;
   }

   public int getSpotId(){
       return spotId;
   }

   public Car getcar(){
       return car;
   }

   public void parkCar(Car car){
       this.car=car;
   }

   public void removeCar(){
       this.car=null;
   }

   public boolean isAvailable(){
       return this.car==null;
   }

   public String toString(){
       return "ParkingSpot{" + "spotId=" + spotId + ", car=" + car + '}';
   }
}

class queue{
   int r,f,size,capacity;
   int []items;

   public queue(int capacity){
       this.r=-1;
       this.f=0;
       this.capacity=capacity;
       this.items=new int[capacity];
   }
   public boolean isEmpty(){
       return size == 0;
   }
   public boolean isFull(){
       return size == capacity;
   }
   public boolean add(int item){
       if(isFull()){
           return false;
       }
           r=(r+1)%capacity;
           items[r]=item;
           size++;
           return true;
       
   }
   public int delete(){
       if(isEmpty()){
           return -1;
       }
       int item = items[f];
       f=(f+1)%capacity;
       size--;
       return item;
       
   }
   public int peek(){
       if(isEmpty()){
           return -1;
       }
       return(items[f]);
   }
}
class Parkinglot{
   private Parkingspot[] parkingSpots;
   public String s[];
   private queue availableSpots;
   public int capacity;

   Parkinglot(int capacity){
       this.capacity=capacity;
       this.parkingSpots=new Parkingspot[capacity];
       this.availableSpots=new queue(capacity);
       this.s=new String[capacity];

       for (int i = 0; i < capacity; i++) {
           parkingSpots[i]=new Parkingspot(i+1);
           availableSpots.add(i);
       }
   }
   
   public boolean parkCar(Car c) {
       if (availableSpots.isEmpty()) {
           System.out.println("Parking lot is full.");
           return false;
       }
       int spotId = availableSpots.delete();
       if (spotId <0 || spotId >= capacity) {
           return false; 
       }
       parkingSpots[spotId].parkCar(c);
       s[spotId]=c.toString();
       System.out.println("Car parked at spot: " + (spotId+1));
       System.out.println();
       return true;
   }
   
   public boolean removeCar(int spotId){
       if(spotId<=0 || spotId>capacity){
           System.out.println("Enter valid spot id \n");
           return false;
       }
       Parkingspot spot = parkingSpots[spotId-1];
       if (!spot.isAvailable()) {
           spot.removeCar();
       availableSpots.add(spotId-1);
       s[spotId-1]=null;
       System.out.println("Car removed from spot: " + spotId);
       System.out.println();
       return true;
       }
       return false;

   }
   public void printAvailableSpots() {
       System.out.println("Available spots:");
       for (int i = 0; i < capacity; i++) {
           Parkingspot spot = parkingSpots[i];
           if (spot.isAvailable()) {
               System.out.println("Spot ID: " + spot.getSpotId());
           }
       }
       System.out.println();
   }
   public String findCar(int spotId){
       if(spotId<=0 || spotId>capacity){
           System.out.println( "Enter a valid spot id");
       }
       return s[spotId-1];
   }
}
public class Main {
   public static void main(String[] args) {
    {
       int ch;
       Scanner sc=new Scanner(System.in);
       Parkinglot parkingLot = new Parkinglot(10);
   do{
       System.out.println("Enter the required operation\n1.Add car \n2.Remove car \n3.Print available space \n4.find car \n5.exit");
       ch=sc.nextInt();
       Car c;
       switch (ch) {
           case 1:
               System.out.println("enter the license no");
               String no=sc.next();
               c=new Car(no);
               parkingLot.parkCar(c);
               parkingLot.printAvailableSpots();
               break;
       
           case 2:
               System.out.println("Enter the spot id to remove");
               int sp=sc.nextInt();
               parkingLot.removeCar(sp);
               parkingLot.printAvailableSpots();
               break;
       
           case 3:
               parkingLot.printAvailableSpots();
               break;
       
           case 4:
               System.out.println("Enter the spot id to find car");
               int find=sc.nextInt();
               String plate=parkingLot.findCar(find);
               if (plate != null) {
                   System.out.println("The car in spot " + find + " is " + plate);
                   System.out.println();
               } else {
                   System.out.println("No car found at spot: " + find);
                   System.out.println();
               }
               break;
       
           case 5:
               break;    
           default:
               System.out.println("enter valid no");
               break;
       }
    
   }while(ch!=5);
}
}}







