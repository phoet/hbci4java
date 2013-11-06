
/*  $Id: Saldo.java,v 1.1 2011/05/04 22:37:49 willuhn Exp $

    This file is part of HBCI4Java
    Copyright (C) 2001-2008  Stefan Palme

    HBCI4Java is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    HBCI4Java is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package org.kapott.hbci.structures;

import java.io.Serializable;
import java.util.Date;

import org.kapott.hbci.manager.HBCIUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/** Darstellung eines Saldos. Anders als bei der Darstellung als
    einfacher Wert wird hier der <em>absolute</em> Betrag des Wertes
    gespeichert. Es gibt ein separates Kennzeichen f�r die
    Unterscheidung zwischen Soll und Haben. */
public final class Saldo
    implements Serializable
{
    /** Betrag des Saldos. */
    public Value  value;
    /** Zeitpunkt der G�ltigkeit dieses Saldos. */
    public Date   timestamp;

    /** Anlegen eines neuen Saldo-Objektes */
    public Saldo()
    {
        value=new Value();
    }

    /** Umwandeln des Saldos in eine String-Darstellung. Das Format ist dabei folgendes:
        <pre>&lt;timestamp> ["+"|"-"] &lt;value></pre>
        @return Stringdarstellung des Saldos */
    public String toString()
    {
        return HBCIUtils.datetime2StringLocal(timestamp)+" "+value.toString();
    }

    public void addResult(Document doc, Element parentElement) {
        Element rootElement = doc.createElement("saldo");
        parentElement.appendChild(rootElement);
        
        Element element = null;

        if (timestamp != null) {
            element = doc.createElement("timestamp");
            element.appendChild(doc.createTextNode(HBCIUtils.date2StringLocal(timestamp)));
            rootElement.appendChild(element);
        }
        if (value != null) {
            element = doc.createElement("value");
            element.appendChild(doc.createTextNode(value.toString()));
            rootElement.appendChild(element);
        }
    }

}
