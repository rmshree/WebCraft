/** \file UserService.java
 * Backend service that works with the User Database.
 */

package app.web.services;

import app.web.domain.User;
import java.util.List;

public interface UserService{

    /** User save(User number);
     *  \brief Saves user into the User Database.
     *  \param user is a User.
     *  \return the saved User or null.
     */
    User save(User user);

    /** User getUserByUsername (String username);
     *  \brief Get User data from database
     *  \param username is a String.
     *  \return a user or NULL
     */
    User getUserByUsername (String username);

    /** User getUserByEmail (String email);
     *  \brief Get User data from database
     *  \param email is a String.
     *  \return a user or NULL
     */

    User getUserByEmail (String email);

    User getCurrentUser();

    List<User> getOnsiteUsers();

    List<User> getOnlineUsers();

}