package annotations;

public class ClassUsingAnnotations {

    @Column(name = "VALID", constraints = @Constraints(nullable = false, index = { "aaa" }))
    public boolean isValid() {
        return false;
    }

}
