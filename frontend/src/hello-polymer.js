import { html, PolymerElement } from '@polymer/polymer/polymer-element.js';

class HelloPolymer extends PolymerElement {
  static get properties() {
    return {
      /** @private */
      _checkboxGroupElement: Object,
    };
  }

  static get template() {
    return html` <h2>Hello World!</h2>
      <div id="content"></div>
      <slot></slot>
      <hr />
      <button on-click="handleClick">Button with server- and client-side event handlers</button>
      <br />
      <vaadin-checkbox-group on-change="handleChange" id="proxy-checkbox" label="" theme="vertical">
        <vaadin-checkbox>English</vaadin-checkbox>
        <vaadin-checkbox>French</vaadin-checkbox>
      </vaadin-checkbox-group>`;
  }

  handleClick() {
    console.log('Button was clicked !!!!');
  }

  /** @protected */
  ready() {
    super.ready();

    this._checkboxGroupElement = this.shadowRoot.querySelector('vaadin-checkbox-group');
    this._checkboxGroupElement.childNodes[0].value = 'En';
    this._checkboxGroupElement.childNodes[1].value = 'Fr';
  }

  static get is() {
    return 'hello-polymer';
  }
}
customElements.define(HelloPolymer.is, HelloPolymer);
