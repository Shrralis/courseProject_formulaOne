package base;

/**
 * Created by shrralis on 3/13/17.
 */
public interface DataFormController {
    enum Type {
        Add,
        Edit,
        Search
    }

    void setType(Type type);
    void setOnMouseOkClickListener(OnMouseClickListener listener);
    void setOnMouseCancelClickListener(OnMouseClickListener listener);
}
