public class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;
    private String medicine;

    public Patient(int id, String name, int age, String disease, String medicine) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.medicine = medicine;
    }

    public Patient(String name, int age, String disease, String medicine) {
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.medicine = medicine;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDisease() { return disease; }
    public String getMedicine() { return medicine; }
}