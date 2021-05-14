import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';
import '@vaadin/vaadin-radio-button/src/vaadin-radio-button.js';
import '@vaadin/vaadin-radio-button/src/vaadin-radio-group.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field';

class NoticeMethodGroupElement extends PolymerElement {
  static get template() {
    return html`
      <style>
        :host {
          display: flex;
          flex: auto;
          margin-top: 0;
        }
        .vaeb-no-top-btm {
          margin-top: 0px;
          margin-bottom: 0px;
          padding-top: 0px;
          padding-bottom: 0px;
        }
      </style>

      <vaadin-radio-group id="z" class="vaeb-no-top-btm" theme="vertical" value="{{methodValue::change}}"
        ><b>Method of Notice</b>
        <vaadin-radio-button value="false">Electronic or Hand delivery</vaadin-radio-button>
        <vaadin-radio-button value="true">Mail - <b>adds 3 additional days</b></vaadin-radio-button>
      </vaadin-radio-group>
    `;
  }

  static get is() {
    return 'notice-method-group';
  }
}

window.customElements.define(NoticeMethodGroupElement.is, NoticeMethodGroupElement);
