import { html, PolymerElement } from '@polymer/polymer/polymer-element.js';

class HelloPolymer extends PolymerElement {
	static get template() {
		return html`
		<h2>Hello World!</h2>
		<div id="content"></div>
		<slot></slot>
		<hr>
		<button on-click="handleClick">Button with server- and client-side event handlers</button>`;
		}
	
	handleClick() {
		console.log('Button was clicked !!!!');
		}
	
	static get is() {
		return 'hello-polymer';
		}
	}
customElements.define(HelloPolymer.is, HelloPolymer);