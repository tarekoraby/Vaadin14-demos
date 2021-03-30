import { html } from 'lit-element';
import '@vaadin/vaadin-text-field/vaadin-text-field.js';
import '@vaadin/vaadin-button/vaadin-button.js';
import { ThemableElement } from './themable-element';

class HelloLit extends ThemableElement {
  render() {
    return html` <div>
      <h1>Hello Lit!</h1>
      <vaadin-text-field id="firstInput"></vaadin-text-field>
      <vaadin-button id="helloButton">Click me!</vaadin-button>
    </div>`;
  }

  static get is() {
    return 'hello-lit';
  }
}

customElements.define('hello-lit', HelloLit);
