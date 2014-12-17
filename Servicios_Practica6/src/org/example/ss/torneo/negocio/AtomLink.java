package org.example.ss.torneo.negocio;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="link")
public class AtomLink {
	
		private String rel;
		private String href;
		private String type;
		
		public AtomLink() {}
		
		public AtomLink(String rel, String href, String type) {
			this.rel = rel;
			this.href = href;
			this.type = type;
		}
		
		public AtomLink(String rel, String href) {
			this(rel,href,"application/xml");
		}

		@XmlAttribute
		public String getRel() {
			return rel;
		}

		
		public void setRel(String rel) {
			this.rel = rel;
		}

		@XmlAttribute
		public String getHref() {
			return href;
		}

		
		public void setHref(String href) {
			this.href = href;
		}

		@XmlAttribute
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
}
