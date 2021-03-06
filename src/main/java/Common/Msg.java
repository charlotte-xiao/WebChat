package Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author charlottexiao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Msg implements Serializable {
    private String fromUser=null;
    private String toUser=null;
    private MsgMIME type=null;
    private Object msg=null;
}
