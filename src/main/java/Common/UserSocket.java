package Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author charlottexiao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSocket {
    private User user=null;
    private Socket socket=null;
    private ObjectOutputStream out=null;
    private ObjectInputStream in=null;
}
