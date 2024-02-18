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
 * Additional details for a completion item label.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class CompletionItemLabelDetails {
  /**
   * An optional string which is rendered less prominently directly after
   * {@link CompletionItem#label}, without any spacing. Should be
   * used for function signatures or type annotations.
   */
  private String detail;

  /**
   * An optional string which is rendered less prominently after
   * {@link #detail}. Should be used for fully qualified
   * names or file path.
   */
  private String description;

  public CompletionItemLabelDetails() {
  }

  /**
   * An optional string which is rendered less prominently directly after
   * {@link CompletionItem#label}, without any spacing. Should be
   * used for function signatures or type annotations.
   */
  public String getDetail() {
    return this.detail;
  }

  /**
   * An optional string which is rendered less prominently directly after
   * {@link CompletionItem#label}, without any spacing. Should be
   * used for function signatures or type annotations.
   */
  public void setDetail(final String detail) {
    this.detail = detail;
  }

  /**
   * An optional string which is rendered less prominently after
   * {@link #detail}. Should be used for fully qualified
   * names or file path.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * An optional string which is rendered less prominently after
   * {@link #detail}. Should be used for fully qualified
   * names or file path.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("detail", this.detail);
    b.add("description", this.description);
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
    CompletionItemLabelDetails other = (CompletionItemLabelDetails) obj;
    if (this.detail == null) {
      if (other.detail != null)
        return false;
    } else if (!this.detail.equals(other.detail))
      return false;
    if (this.description == null) {
      if (other.description != null)
        return false;
    } else if (!this.description.equals(other.description))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.detail== null) ? 0 : this.detail.hashCode());
    return prime * result + ((this.description== null) ? 0 : this.description.hashCode());
  }
}
