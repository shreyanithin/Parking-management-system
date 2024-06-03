 class Car{
    private String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String toString(){
        return "Car{" + "licensePlate='" + licensePlate + '\'' + '}';
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
        if(size==0)
            return true;
        else
            return false;
    }
    public boolean isFull(){
        if(size==capacity)
            return true;
        else
            return false;
    }
    public boolean add(int item){
        if(isFull()){
            return false;
        }
        else{
            r=(r+1)%capacity;
            items[r]=item;
            size++;
            return true;
        }
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
    private queue availableSpots;
    public int capacity;

    Parkinglot(int capacity){
        this.capacity=capacity;
        this.parkingSpots=new Parkingspot[capacity];
        this.availableSpots=new queue(capacity);

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
        if (spotId <= 0 || spotId > capacity) {
            return false; 
        }
        parkingSpots[spotId - 1].parkCar(c);
        System.out.println("Car parked at spot: " + spotId);
        return true;
    }
    
    public boolean removeCar(int spotId){
        if(spotId<1 || spotId>capacity){
            return false;
        }
        Parkingspot spot = parkingSpots[spotId - 1];
        if (!spot.isAvailable()) {
            spot.removeCar();
        availableSpots.add(spotId);
        System.out.println("Car removed from spot: " + spotId);
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
}
 }
 public class Main {
    public static void main(String[] args) {
        Parkinglot parkingLot = new Parkinglot(10);

        Car car1 = new Car("A123");
        Car car2 = new Car("B789");
        Car car3 = new Car("C456");

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);

        parkingLot.printAvailableSpots();

        parkingLot.removeCar(2);
        parkingLot.printAvailableSpots();

        parkingLot.parkCar(new Car("D101"));
        parkingLot.printAvailableSpots();
    }
}







