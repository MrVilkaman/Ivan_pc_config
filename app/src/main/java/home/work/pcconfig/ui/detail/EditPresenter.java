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
    private boolean isUpdateMode;

    @Inject
    EditPresenter(OrderInteractor interactor, Router router) {
        this.interactor = interactor;
        this.router = router;
    }

    @Override
    public void onViewAttached() {
        super.onViewAttached();

        subscribeUI(interactor.loadOrderItem(),new ViewSubscriber<EditView, OrderItem>(){
            @Override
            public void onNext(OrderItem orderItem) {
                isUpdateMode = true;
                view().showUpdateState();
                view().fillFields(orderItem);
            }
        });
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
        subscribeUI(interactor.createOrUpdateOrder(orderItem), new CreateSubscriber(router,isUpdateMode));
    }

    private class CreateSubscriber extends ViewSubscriber<EditView, Object> {
        private final Router router;
        private final boolean isUpdateMode;

        CreateSubscriber(Router router, boolean isUpdateMode) {
            this.router = router;
            this.isUpdateMode = isUpdateMode;
        }

        @Override
        public void onComplete() {
            super.onComplete();
            if (isUpdateMode) {
                uiResolver().showToast(R.string.success_update);
            }else {
                uiResolver().showToast(R.string.success_create);
            }
            router.exit();
        }
    }
}
