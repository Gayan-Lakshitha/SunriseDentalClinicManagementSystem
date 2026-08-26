/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

public class Session {

    private static int userId;
    private static String name;
    private static String email;
    private static String role;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Session.userId = userId;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Session.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Session.role = role;
    }

    public static void clearSession() {

        userId = 0;
        name = null;
        email = null;
        role = null;
    }
}
