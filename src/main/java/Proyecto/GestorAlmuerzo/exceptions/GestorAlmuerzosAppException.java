package proyecto.gestoralmuerzo.exceptions;

public class GestorAlmuerzosAppException extends Exception{
    public static final String emptyPassword = "Contraseña Vacia, Porfavor llenar el campo";
    public static final String incorrectinformation = "Información Incorrecta. Porfavor verifique datos";
    public static final String emailexist = "El correo ya esta registrado, por favor escoja otro";
    public static final String emptyemail = "El usuario debe rellenar el campo de email";
    public static final String notpdconcident = "Ambas contraseñas no coinciden , por favor revisar";

    public static final String nameempty = "Falta Nombre";
    public static final String lastnameempty = "Falta Apellido";

    public  static final String ingredientinuse = "El ingrediente esta en uso, porfavor borrelo de los platos e" +
            " intentelo otra vez";

    public static final String emailnotexist = "El correo no esta registrado, por favor ingrese un correo valido";

    public static final String rolenotexist = "El rol asignado no existe";
    public GestorAlmuerzosAppException(String msm) {
        super(msm);
    }
}
