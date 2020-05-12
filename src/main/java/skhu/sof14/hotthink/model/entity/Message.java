package skhu.sof14.hotthink.model.entity;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@ToString
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_idx")
    int id;

    @Column(name = "message_content")
    String content;

    @Column(name = "message_date_time")
    LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_receiver")
    User receiver;

    @ManyToOne
    @JoinColumn(name = "user_sender")
    User sender;
}
