public class MainTask {
    public static void main(String[] args) {

        Task task = new Task();

        TeamMember dev = new TeamMember("Dev");
        TeamMember tester = new TeamMember("Tester");

        task.register(dev);
        task.register(tester);

        task.setStatus("In Progress");
        task.setStatus("Done");
    }
}
