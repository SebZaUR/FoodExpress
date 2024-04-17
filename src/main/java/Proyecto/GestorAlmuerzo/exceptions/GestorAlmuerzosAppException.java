package proyecto.gestoralmuerzo.exceptions;

public class GestorAlmuerzosAppException extends Exception{
    public static final String empty_password = "Contraseña Vacia, Porfavor llenar el campo";
    public static final String incorrect_information = "Información Incorrecta. Porfavor verifique datos";
    public static final String email_exist = "El correo ya esta registrado, por favor escoja otro";
    public static final String empty_email = "El usuario debe rellenar el campo de email";
    public static final String not_pd_concident = "Ambas contraseñas no coinciden , por favor revisar";
    public static final String name_empty = "Falta Nombre";
    public static final String last_name_empty = "Falta Apellido";

    public  static final String ingredient_in_use = "El ingrediente esta en uso, porfavor borrelo de los platos e" +
            " intentelo otra vez";

    public static final String email_not_exist = "El correo no esta registrado, por favor ingrese un correo valido";

    public static final String role_not_exist = "El rol asignado no existe";
    public GestorAlmuerzosAppException(String msm) {
        super(msm);
    }
}
