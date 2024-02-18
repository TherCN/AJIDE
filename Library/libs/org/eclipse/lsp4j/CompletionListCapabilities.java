/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import java.util.List;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The client supports the following {@link CompletionList} specific
 * capabilities.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class CompletionListCapabilities {
  /**
   * The client supports the following itemDefaults on
   * a completion list.
   * <p>
   * The value lists the supported property names of the
   * {@link CompletionList#itemDefaults} object. If omitted,
   * no properties are supported.
   */
  private List<String> itemDefaults;

  public CompletionListCapabilities() {
  }

  public CompletionListCapabilities(final List<String> itemDefaults) {
    this.itemDefaults = itemDefaults;
  }

  /**
   * The client supports the following itemDefaults on
   * a completion list.
   * <p>
   * The value lists the supported property names of the
   * {@link CompletionList#itemDefaults} object. If omitted,
   * no properties are supported.
   */
  public List<String> getItemDefaults() {
    return this.itemDefaults;
  }

  /**
   * The client supports the following itemDefaults on
   * a completion list.
   * <p>
   * The value lists the supported property names of the
   * {@link CompletionList#itemDefaults} object. If omitted,
   * no properties are supported.
   */
  public void setItemDefaults(final List<String> itemDefaults) {
    this.itemDefaults = itemDefaults;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("itemDefaults", this.itemDefaults);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CompletionListCapabilities other = (CompletionListCapabilities) obj;
    if (this.itemDefaults == null) {
      if (other.itemDefaults != null)
        return false;
    } else if (!this.itemDefaults.equals(other.itemDefaults))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.itemDefaults== null) ? 0 : this.itemDefaults.hashCode());
  }
}
