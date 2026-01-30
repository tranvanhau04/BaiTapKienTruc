public interface Subject {
    void attach(Observer o);   // đăng ký theo dõi
    void detach(Observer o);   // hủy theo dõi
    void notifyObservers();    // thông báo thay đổi
}
