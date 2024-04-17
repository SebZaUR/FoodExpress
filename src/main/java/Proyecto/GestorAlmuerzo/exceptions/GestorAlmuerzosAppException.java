package proyecto.gestoralmuerzo.exceptions;

public class GestorAlmuerzosAppException extends Exception{
    public static final String EMPTY_PD = "Contraseña Vacia, Porfavor llenar el campo";
    public static final String INCORRECT_INFORMATION = "Información Incorrecta. Porfavor verifique datos";
    public static final String EMAIL_EXIST = "El correo ya esta registrado, por favor escoja otro";
    public static final String EMPTY_EMAIL = "El usuario debe rellenar el campo de email";
    public static final String NOT_PD_CONCIDENT = "Ambas contraseñas no coinciden , por favor revisar";
    public static final String NAME_EMPTY = "Falta Nombre";
    public static final String LAST_NAME_EMPTY = "Falta Apellido";

    public static final String INGREDIENT_IN_USE = "El ingrediente esta en uso, porfavor borrelo de los platos e" +
            " intentelo otra vez";

    public static final String EMAIL_NOT_EXIST = "El correo no esta registrado, por favor ingrese un correo valido";

    public static final String ROLE_NOT_EXIST = "El rol asignado no existe";

    public GestorAlmuerzosAppException(String msm) {
        super(msm);
    }
}
