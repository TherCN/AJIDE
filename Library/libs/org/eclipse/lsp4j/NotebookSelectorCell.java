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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The cell of the matching notebook to be synced.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookSelectorCell {
  /**
   * The cells of the matching notebook to be synced.
   */
  @NonNull
  private String language;

  public NotebookSelectorCell() {
  }

  public NotebookSelectorCell(@NonNull final String language) {
    this.language = Preconditions.<String>checkNotNull(language, "language");
  }

  /**
   * The cells of the matching notebook to be synced.
   */
  @NonNull
  public String getLanguage() {
    return this.language;
  }

  /**
   * The cells of the matching notebook to be synced.
   */
  public void setLanguage(@NonNull final String language) {
    this.language = Preconditions.checkNotNull(language, "language");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("language", this.language);
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
    NotebookSelectorCell other = (NotebookSelectorCell) obj;
    if (this.language == null) {
      if (other.language != null)
        return false;
    } else if (!this.language.equals(other.language))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.language== null) ? 0 : this.language.hashCode());
  }
}
