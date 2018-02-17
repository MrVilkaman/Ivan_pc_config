package home.work.pcconfig.ui.detail;


import com.github.mrvilkaman.presentationlayer.fragments.core.BasePresenter;
import com.github.mrvilkaman.presentationlayer.subscriber.ViewSubscriber;

import java.util.Arrays;

import javax.inject.Inject;

import home.work.pcconfig.R;
import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.business.orders.OrderInteractor;
import ru.terrakok.cicerone.Router;

public class EditPresenter extends BasePresenter<EditView> {

    OrderInteractor interactor;
    Router router;

    @Inject
    EditPresenter(OrderInteractor interactor, Router router) {
        this.interactor = interactor;
        this.router = router;
    }

    void doAction(int cpu,
                  int ram,
                  String matherboard,
                  String gpu,
                  String storageSize,
                  boolean isSsd,
                  boolean isGamingViewChecked) {
        if (cpu == -1 || ram == -1) {
            uiResolver().showToast(R.string.check_fields);
            return;
        }

        for (String item : Arrays.asList(matherboard, gpu, storageSize)) {
            if (item.isEmpty()) {
                uiResolver().showToast(R.string.check_fields);
                return;
            }
        }
        final int storage;
        try {
            storage = Integer.parseInt(storageSize);
        } catch (NumberFormatException e) {
            uiResolver().showToast(R.string.invalid_ram_field);
            return;
        }

        final OrderItem orderItem = new OrderItem(-1, matherboard, cpu, gpu, ram, storage, isSsd, isGamingViewChecked);
        subscribeUI(interactor.createOrUpdateOreder(orderItem), new CreateSubscriber(router));
    }

    private class CreateSubscriber extends ViewSubscriber<EditView, Object> {
        private final Router router;

        public CreateSubscriber(Router router) {
            this.router = router;
        }

        @Override
        public void onComplete() {
            super.onComplete();
            uiResolver().showToast(R.string.success_create);
            router.exit();
        }
    }
}
