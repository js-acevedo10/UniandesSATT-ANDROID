package com.example.juansantiagoacev.uniandessatt.Helpers;

import com.example.juansantiagoacev.uniandessatt.DTO.Alerta;
import com.example.juansantiagoacev.uniandessatt.DTO.Evento;
import com.example.juansantiagoacev.uniandessatt.DTO.User;

/**
 * Created by juansantiagoacev on 5/11/16.
 */
public class UserHelper {

    public static User currentUser;

    public static Evento selectedEvento;

    public static Alerta selectedAlerta;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserHelper.currentUser = currentUser;
    }

    public static Evento getSelectedEvento() {
        return selectedEvento;
    }

    public static void setSelectedEvento(Evento selectedEvento) {
        UserHelper.selectedEvento = selectedEvento;
    }

    public static Alerta getSelectedAlerta() {
        return selectedAlerta;
    }

    public static void setSelectedAlerta(Alerta selectedAlerta) {
        UserHelper.selectedAlerta = selectedAlerta;
    }
}
