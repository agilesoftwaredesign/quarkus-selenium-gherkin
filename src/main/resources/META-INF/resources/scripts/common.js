class DetailElement extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        const template = document.getElementById('detail-template');
        this.shadowRoot.appendChild(template.content.cloneNode(true));
    }
}
customElements.define('detail-page', DetailElement);

class FormElement extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        const template = document.getElementById('forms-template');
        this.shadowRoot.appendChild(template.content.cloneNode(true));
        
        this.shadowRoot.querySelectorAll('button').forEach(b => b.addEventListener('click', this.sub));
    }
    
    sub(evt) {
    	evt.preventDefault();
    	console.log(evt.target.form);
    	const one = evt.target.form['inp-one'].value;
    	const two = evt.target.form['inp-tel'].value;
        fetch('./forms?one='+one+'&tel='+two, {})
        	.then(response => Promise.all([response.ok, response.status, response.json()]))
        	.then(([responseOk, status, json]) => {
                if (responseOk) {
                	document.querySelector('main').innerHTML = JSON.stringify(json);
                } else {
                	alert(`Failed with status ${status} and ${json.error.message}`);
                }
        	});
    }
}
customElements.define('forms-page', FormElement);

function clickNavigationLink(event) {
    event.preventDefault();
    const elementName = event.target.hash.substring(1);
    document.querySelector('main').innerHTML = `<${elementName}>`;
}

// adds click listeners to the navigation links
document
    .querySelectorAll("a.navigation")
    .forEach(element => element.addEventListener('click', clickNavigationLink));