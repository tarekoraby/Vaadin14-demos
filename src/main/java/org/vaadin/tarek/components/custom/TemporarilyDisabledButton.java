package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;

public class TemporarilyDisabledButton extends Button {

    final int DEFAULT_TIMEOUT = 1000;
    int timeout;
    String timeoutCaption;

    public TemporarilyDisabledButton() {
        setTimeout(this.DEFAULT_TIMEOUT);
    }

    public TemporarilyDisabledButton(String text) {
        super(text);
        setTimeout(this.DEFAULT_TIMEOUT);
    }

    public TemporarilyDisabledButton(String text, String timeoutCaption) {
        super(text);
        setTimeoutCaption(timeoutCaption);
        setTimeout(this.DEFAULT_TIMEOUT);
    }

    public TemporarilyDisabledButton(String text, int timeout) {
        super(text);
        setTimeout(timeout);
    }

    public TemporarilyDisabledButton(String text, String timeoutCaption,
            int timeout) {
        super(text);
        setTimeoutCaption(timeoutCaption);
        setTimeout(timeout);
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getTimeoutCaption() {
        return this.timeoutCaption;
    }

    public void setTimeoutCaption(String timeoutCaption) {
        this.timeoutCaption = timeoutCaption;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        String str = "{myButton = $0;"
                + "    var oldValue = myButton.textContent;"
                + "    myButton.setAttribute('disabled', true);"
                + "    timeoutCaption = $1;"
                + "    if (timeoutCaption) { "
                + "        myButton.textContent = timeoutCaption;}"
                + "    setTimeout(function(){"
                + "        myButton.textContent = oldValue;"
                + "        myButton.removeAttribute('disabled');"
                + "    }, $2)}";
        getElement().executeJs(
                "this.addEventListener(\"click\", () =>" + str + ")",
                getElement(), getTimeoutCaption(), getTimeout());
    }
}