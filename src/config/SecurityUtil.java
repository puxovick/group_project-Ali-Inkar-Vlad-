package config;
import entity.User;
public class SecurityUtil {
    public static boolean hasAccess(User user, String requiredRole) {
        if (user == null || user.getRole() == null) return false;
        String userRole = user.getRole().toUpperCase();
        requiredRole = requiredRole.toUpperCase();
        if (userRole.equals("ADMIN")) return true;
        if (userRole.equals("MANAGER") && !requiredRole.equals("ADMIN")) return true;
        return userRole.equals(requiredRole);
    }
}