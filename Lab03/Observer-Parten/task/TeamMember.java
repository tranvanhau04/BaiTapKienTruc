public class TeamMember implements TaskObserver {

    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("Notification to " + name + 
                   ": Task status has been updated to \"" + status + "\"");
    }
}
