public class process {
    public class Process{
        int productID, exectionTime;
        String name, user;
        process next;
        process previous;


        public Process(int productID, String name, String user, int exectionTime) {
            this.productID = productID;
            this.name = name;
            this.user = user;
            this.exectionTime = exectionTime;
            this.next = null;
            this.previous = null;
        }
    }
}
