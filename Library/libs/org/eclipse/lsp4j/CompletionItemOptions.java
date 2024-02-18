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

import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The server supports the following {@link CompletionItem} specific capabilities.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class CompletionItemOptions {
  /**
   * The server has support for completion item label details (see also {@link CompletionItemLabelDetails}) when receiving
   * a completion item in a resolve call.
   */
  private Boolean labelDetailsSupport;

  public CompletionItemOptions() {
  }

  public CompletionItemOptions(final Boolean labelDetailsSupport) {
    this.labelDetailsSupport = labelDetailsSupport;
  }

  /**
   * The server has support for completion item label details (see also {@link CompletionItemLabelDetails}) when receiving
   * a completion item in a resolve call.
   */
  public Boolean getLabelDetailsSupport() {
    return this.labelDetailsSupport;
  }

  /**
   * The server has support for completion item label details (see also {@link CompletionItemLabelDetails}) when receiving
   * a completion item in a resolve call.
   */
  public void setLabelDetailsSupport(final Boolean labelDetailsSupport) {
    this.labelDetailsSupport = labelDetailsSupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("labelDetailsSupport", this.labelDetailsSupport);
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
    CompletionItemOptions other = (CompletionItemOptions) obj;
    if (this.labelDetailsSupport == null) {
      if (other.labelDetailsSupport != null)
        return false;
    } else if (!this.labelDetailsSupport.equals(other.labelDetailsSupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.labelDetailsSupport== null) ? 0 : this.labelDetailsSupport.hashCode());
  }
}
