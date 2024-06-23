package business_logic.Dan.rowsum.dto;

import business_logic.Dan.rowsum.domain.Payment;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Getter
@EqualsAndHashCode(exclude = {"id", "paymentMethod", "price"})
public class PaymentDto {
    private Long id;
    private Long ownerId; // 업주 Id
    private LocalDate payDate; // 결제일
    private String calculateCode; // 계산방식을 선택하는 계산코드
    private Payment.Method paymentMethod; // 결제방식
    private int price;

    public PaymentDto(Payment entity) {
        this.id = entity.getId();
        this.ownerId = entity.getOwnerId();
        this.payDate = entity.getPayDate();
        this.calculateCode = entity.getCalculateCode();
        this.paymentMethod = entity.getMethod();
        this.price = entity.getPrice();
    }

    public static Stream<List<PaymentDto>> classify(List<Payment> payments){
        Map<PaymentDto, List<PaymentDto>> classifiedPayment = new LinkedHashMap<>();

        for (Payment payment : payments) {
            PaymentDto dto = new PaymentDto(payment);
            List<PaymentDto> list = classifiedPayment.get(dto);

            if(list != null){
                list.add(dto);
            } else {
                classifiedPayment.put(dto, new ArrayList<>(Collections.singletonList(dto)));
            }
        }

        return classifiedPayment.entrySet().stream()
                .map(Map.Entry::getValue);
    }

}