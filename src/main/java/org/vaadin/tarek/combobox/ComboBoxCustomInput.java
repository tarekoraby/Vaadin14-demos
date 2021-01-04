package org.vaadin.tarek.combobox;

import java.util.Collection;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;

public class ComboBoxCustomInput<T> extends ComboBox<T> {

    /**
     * Creates an empty combo box with the defined page size for lazy loading.
     * <p>
     * The default page size is 50.
     * <p>
     * The page size is also the largest number of items that can support
     * client-side filtering. If you provide more items than the page size, the
     * component has to fall back to server-side filtering.
     *
     * @param pageSize
     *            the amount of items to request at a time for lazy loading
     * @see {@link #setPageSize(int)}
     */
    public ComboBoxCustomInput(int pageSize) {
        super(pageSize);
    }

    /**
     * Default constructor. Creates an empty combo box.
     */
    public ComboBoxCustomInput() {
        super();
    }

    /**
     * Creates an empty combo box with the defined label.
     *
     * @param label
     *            the label describing the combo box
     */
    public ComboBoxCustomInput(String label) {
        super(label);
    }

    /**
     * Creates a combo box with the defined label and populated with the items
     * in the collection.
     *
     * @param label
     *            the label describing the combo box
     * @param items
     *            the items to be shown in the list of the combo box
     * @see #setItems(Collection)
     */
    public ComboBoxCustomInput(String label, Collection<T> items) {
        super(label, items);
    }

    /**
     * Creates a combo box with the defined label and populated with the items
     * in the array.
     *
     * @param label
     *            the label describing the combo box
     * @param items
     *            the items to be shown in the list of the combo box
     * @see #setItems(Object...)
     */
    @SafeVarargs
    public ComboBoxCustomInput(String label, T... items) {
        super(label, items);
    }

    /**
     * Adds the given component into this this ComboBox input's field before the
     * content, replacing any existing prefix component.
     * <p>
     * This is most commonly used to add a simple icon or static text into the
     * field.
     *
     * @param component
     *            the component to set, can be {@code null} to remove existing
     *            prefix component
     */
    public void setPrefixComponent(Component component) {
        // remove existing prefix if one is present
        getElement().executeJs("var prefix = this.$.input.querySelector('[part=\"custom-combo-box-prefix\"]');"
                + "if (prefix) {prefix.remove();}");

        if (component != null) {
            component.getElement().setAttribute("part", "custom-combo-box-prefix");
            getElement().executeJs("this.$.input.querySelector('[slot=\"prefix\"]').innerHTML = '"
                    + removeLineBreak(component.getElement().toString()) + "';");

        }
    }

    /**
     * Adds the given component into this ComboBox input's field after the
     * content, replacing any existing suffix component.
     * <p>
     * This is most commonly used to add a simple icon or static text into the
     * field.
     *
     * @param component
     *            the component to set, can be {@code null} to remove existing
     *            suffix component
     */
    public void setSuffixComponent(Component component) {
        System.out.println(component.getElement().toString());
        // remove existing suffix if one is present
        getElement()
                .executeJs("var suffix = this.$.input.shadowRoot.querySelector('[part=\"custom-combo-box-suffix\"]');"
                        + "if (suffix) {suffix.remove();}");

        if (component != null) {
            component.getElement().setAttribute("part", "custom-combo-box-suffix");
            getElement().executeJs("var inputField = this.$.input.shadowRoot.querySelector('[part=\"input-field\"]');"
                    + "var suffix = this.$.input.shadowRoot.querySelector('[name=\"suffix\"]');"
                    + "var newSuffix = document.createElement('div');\n" + "newSuffix.innerHTML ='"
                    + removeLineBreak(component.getElement().toString()) + "';"
                    + "inputField.insertBefore(newSuffix, suffix);");
        }
    }

    public static String removeLineBreak(String s) {
        return s.replace("\n", "").replace("\r", "");
    }
}
