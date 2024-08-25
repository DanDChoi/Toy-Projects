package equals_hashcode.Dan.rowsum.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long ownerId;

    @Column
    private LocalDate payDate; // 결제일

    @Column
    private String calculateCode; // 계산방식을 선택하는 계산코드

    @Column
    @Enumerated(EnumType.STRING)
    private Method method; // 결제방식

    @Column
    private int price;

    @Builder
    public Payment(Long ownerId, LocalDate payDate, String calculateCode, Method method, int price) {
        this.ownerId = ownerId;
        this.payDate = payDate;
        this.calculateCode = calculateCode;
        this.method = method;
        this.price = price;
    }

    public enum Method {
        MOBILE("휴대폰"),
        CREDIT_CARD("신용카드"),
        CASH("현금");

        private String text;

        Method(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

}

