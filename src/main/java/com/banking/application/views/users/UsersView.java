package com.banking.application.views.users;

import com.banking.application.DTO.TransactionHistoryDTO;
import com.banking.application.data.service.SamplePersonService;
import com.banking.application.views.MainLayout;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.RolesAllowed;

import java.awt.*;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Users")
@Route(value = "master-detail/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Tag("users-view")
@JsModule("./views/users/users-view.ts")
@Uses(Icon.class)
public class UsersView extends LitTemplate implements HasStyle, BeforeEnterObserver {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "master-detail/%s/edit";

    // This is the Java companion file of a design
    // You can find the design file inside /frontend/views/
    // The design can be easily edited by using Vaadin Designer
    // (vaadin.com/designer)

    @Id
    private Grid<TransactionHistoryDTO> grid;

    @Id
    private TextField ID;
    @Id
    private TextField Sender;
    @Id
    private TextField Receiver;
    @Id
    private NumberField MoneySent;
    @Id
    private DatePicker Date;
    @Id
    private Checkbox important;

    @Id
    private Button cancel;
    @Id
    private Button save;

    private BeanValidationBinder<TransactionHistoryDTO> binder;

    private TransactionHistoryDTO samplePerson;

    private final SamplePersonService samplePersonService;

    public UsersView(SamplePersonService samplePersonService) {
        this.samplePersonService = samplePersonService;
        addClassNames("users-view");
        grid.addColumn(TransactionHistoryDTO::getSender).setHeader("ID").setSortProperty("ID")
                .setAutoWidth(true);
        grid.addColumn(TransactionHistoryDTO::getSender).setHeader("Sender").setSortProperty("Sender")
                .setAutoWidth(true);
        grid.addColumn(TransactionHistoryDTO::getReceiver).setHeader("Receiver").setSortProperty("Receiver").setAutoWidth(true);
        grid.addColumn(TransactionHistoryDTO::getDate).setHeader("Date").setSortProperty("Date").setAutoWidth(true);
        grid.addColumn(TransactionHistoryDTO::getMoneySent).setHeader("MoneySent").setSortProperty("MoneySent")
                .setAutoWidth(true);
        LitRenderer<TransactionHistoryDTO> importantRenderer = LitRenderer.<TransactionHistoryDTO>of(
                        "<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
                .withProperty("icon", important -> important != null ? "check" : "minus").withProperty("color",important -> important != null ? "var(--lumo-primary-text-color)" : "var(--lumo-disabled-text-color)");

        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getID()));
            } else {
                clearForm();
                UI.getCurrent().navigate(UsersView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(TransactionHistoryDTO.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.samplePerson == null) {
                    this.samplePerson = new TransactionHistoryDTO();
                }
                binder.writeBean(this.samplePerson);
                samplePersonService.update(this.samplePerson);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(UsersView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> samplePersonId = event.getRouteParameters().get(SAMPLEPERSON_ID).map(Long::parseLong);
        if (samplePersonId.isPresent()) {
            TransactionHistoryDTO samplePersonFromBackend = samplePersonService.get((int) samplePerson.getID());
            if (samplePersonFromBackend != null) {
                populateForm(samplePersonFromBackend);
            } else {
                Notification.show(
                        String.format("The requested samplePerson was not found, ID = %s", samplePersonId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(UsersView.class);
            }
        }
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(TransactionHistoryDTO value) {
        this.samplePerson = value;
        binder.readBean(this.samplePerson);

    }
}
