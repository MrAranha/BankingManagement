package com.banking.application.views.transactions;

import com.banking.application.data.service.SamplePersonService;
import com.banking.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

import javax.sound.midi.Receiver;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@PageTitle("Transactions")
@Route(value = "transaction", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class TransactionsView extends Composite<VerticalLayout> {

    TransactionAddHandler _transaction;

    public TransactionsView(TransactionAddHandler transaction) {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        TextField textField = new TextField();
        DatePicker datePicker = new DatePicker();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        TextField textField2 = new TextField();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        NumberField textField4 = new NumberField();
        NumberField textField5 = new NumberField();
        com.vaadin.flow.component.checkbox.Checkbox checkbox = new com.vaadin.flow.component.checkbox.Checkbox();
        com.vaadin.flow.component.checkbox.Checkbox checkboxUpdate = new com.vaadin.flow.component.checkbox.Checkbox();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        VerticalLayout layoutColumn6 = new VerticalLayout();
        getContent().setWidthFull();
        getContent().addClassName(Padding.LARGE);
        layoutRow.setWidthFull();
        layoutRow.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth(null);
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth(null);
        h3.setText("Fazer Transação");
        layoutRow2.setWidthFull();
        layoutRow2.addClassName(Gap.LARGE);
        layoutRow2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth(null);
        textField.setLabel("Sender");
        textField5.setLabel("ID");
        checkbox.setLabel("Delete?");
        checkboxUpdate.setLabel("Update?");
        textField.setWidthFull();
        textField5.setWidthFull();
        datePicker.setLabel("Date");
        datePicker.setWidthFull();
        layoutRow2.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth(null);
        textField2.setLabel("Receiver");
        textField2.setWidthFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidthFull();
        textField4.setLabel("MoneySent");
        textField4.setWidthFull();
        layoutRow4.addClassName(Gap.MEDIUM);
        buttonPrimary.setText("Save");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow.setFlexGrow(1.0, layoutColumn6);
        layoutColumn6.setWidth(null);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn5);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(textField);
        layoutColumn3.add(textField5);
        layoutColumn3.add(datePicker);
        layoutRow2.add(layoutColumn4);
        layoutColumn4.add(textField2);
        layoutColumn4.add(layoutRow3);
        layoutColumn4.add(textField4);
        layoutColumn2.add(layoutRow4);
        layoutRow4.add(buttonPrimary);
        layoutRow4.add(checkbox);
        layoutRow4.add(checkboxUpdate);
        layoutRow.add(layoutColumn6);
        _transaction = transaction;
        buttonPrimary.addClickListener(e -> _transaction.addTransaction(((long)(double)textField5.getValue()), textField.getValue(), textField2.getValue(), Date.from((datePicker.getValue()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), textField4.getValue(), checkbox.getValue(), checkboxUpdate.getValue()));
    }
}
