package eu.greyson.bsc.bscTest.service.dto;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/** Queue to store payments. */
public class PaymentsQueue extends ConcurrentLinkedQueue<Payment> {
    @Override
    public boolean add(final Payment payment) {
        if(contains(payment)) {
            stream().filter(payment::equals).forEach(p -> {
                p.addAmount(payment.getAmount());
                if(p.getAmount() == 0) {
                    remove(p);
                }
            });
            return true;
        }
        else {
            return payment.getAmount() != 0 && super.add(payment);
        }
    }

    @Override
    public boolean addAll(Collection<? extends Payment> c) {
        c.forEach(this::add);
        return true;
    }
}
