import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-split-layout/src/vaadin-split-layout.js';

class MyView3 extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-split-layout orientation="vertical" style="width: 100%; height: 100%;"></vaadin-split-layout>
`;
    }

    static get is() {
        return 'my-view3';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MyView3.is, MyView3);
