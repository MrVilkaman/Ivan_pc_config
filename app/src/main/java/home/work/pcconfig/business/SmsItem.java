package home.work.pcconfig.business;


public class SmsItem {

    private String phone;
    private String text;

    public SmsItem(String phone, String text) {
        this.phone = phone;
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }

    public String getText() {
        return text;
    }
}
