package in.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by amardeep2551 on 8/24/2017.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SampleRequestMessage {

    private String message;

}
