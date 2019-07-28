package ee.ut.esi.group4.buildit.rental.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor(force=true,access= AccessLevel.PROTECTED)
@AllArgsConstructor(staticName="of")
public class StatusMessagingData {

    @Id @GeneratedValue
    Long entityId;
    @Enumerated(EnumType.STRING)
    POStatus state;
    String client;
    String comment;

//
//    public static StatusMessagingData of(Long entityId, POStatus state, String comment){
//        StatusMessagingData po = new StatusMessagingData();
//
//        po.entityId = entityId;
//        po.state = state
//        po.comment = comment.isEmpty() ? "" : comment;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        po.client  = authentication.getName();
//
//        return po;
//    }
//
//    public static StatusMessagingData of(StatusMessagingData statusMessagingData){
//        StatusMessagingData po = new StatusMessagingData();
//        po.entityId = statusMessagingData.entityId;
//        po.state = statusMessagingData.state;
//        po.comment = statusMessagingData.comment.isEmpty() ? "" : statusMessagingData.comment;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        po.client  = authentication.getName();
//
//        return po;
//    }

}