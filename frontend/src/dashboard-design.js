import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-board/src/vaadin-board.js';
import '@vaadin/vaadin-board/src/vaadin-board-row.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

class DashboardDesign extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;" theme="view-base">
 <vaadin-horizontal-layout>
  <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1; width: 300px; height: 300px;" theme="main">
   <vaadin-vertical-layout style="background: linear-gradient(to bottom, #0076B9, #0076B9, #1C86C3, #3D9ACF, #71B9E2); align-items: center; justify-content: center;" theme="card-s" id="vlaycrm"></vaadin-vertical-layout>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1; width: 300px; height: 300px;" theme="main">
   <vaadin-vertical-layout theme="card-s" style="background: linear-gradient(to bottom, #FF8400, #EFAB62, #F5B066); align-items: center; justify-content: center;" id="vlayquriosity"></vaadin-vertical-layout>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1; width: 300px;" theme="main">
   <vaadin-vertical-layout theme="card-s" style="background: linear-gradient(to bottom, #E8515F, #F17783, #F19099); align-items: center; justify-content: center;" id="vlaycommessa"></vaadin-vertical-layout>
  </vaadin-vertical-layout>
 </vaadin-horizontal-layout>
 <vaadin-board style="width: 100%;">
  <vaadin-board-row>
   <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1;" theme="main">
    <vaadin-vertical-layout theme="card-s" id="vaadinVerticalLayout"></vaadin-vertical-layout>
   </vaadin-vertical-layout>
   <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1;" theme="main">
    <vaadin-vertical-layout theme="card-s" id="vaadinVerticalLayout1"></vaadin-vertical-layout>
   </vaadin-vertical-layout>
  </vaadin-board-row>
  <vaadin-board-row>
   <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1;" theme="main">
    <vaadin-vertical-layout theme="card-s" id="vaadinVerticalLayout2"></vaadin-vertical-layout>
   </vaadin-vertical-layout>
   <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1;" theme="main">
    <vaadin-vertical-layout theme="card-s" id="vlaydett"></vaadin-vertical-layout>
   </vaadin-vertical-layout>
   <vaadin-vertical-layout style="overflow-y:auto; flex-grow: 1;" theme="main">
    <vaadin-vertical-layout theme="card-s"></vaadin-vertical-layout>
   </vaadin-vertical-layout>
  </vaadin-board-row>
 </vaadin-board>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'dashboard-design';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(DashboardDesign.is, DashboardDesign);
