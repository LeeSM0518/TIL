package class_class.new_instance_method;

public class NewInstanceExample {
    public static void main(String[] args) {
        try{
            Class clazz = Class.forName("class_class.new_instance_method.Action");
            Action action = (Action) clazz.newInstance();
            action.execute();
            Class clazz2 = Class.forName("class_class.new_instance_method.ReceiveAction");
            ReceiveAction receiveAction = (ReceiveAction) clazz2.newInstance();
            receiveAction.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
