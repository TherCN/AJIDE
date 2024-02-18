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
 * A notebook document filter denotes a notebook document by
 * different properties.
 * <p>
 * At least one of either {@link #notebookType}, {@link #scheme},
 * or {@link #pattern} is required.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentFilter {
  /**
   * The type of the enclosing notebook.
   */
  private String notebookType;

  /**
   * A Uri scheme, like `file` or `untitled`.
   */
  private String scheme;

  /**
   * A glob pattern.
   */
  private String pattern;

  public NotebookDocumentFilter() {
  }

  /**
   * The type of the enclosing notebook.
   */
  public String getNotebookType() {
    return this.notebookType;
  }

  /**
   * The type of the enclosing notebook.
   */
  public void setNotebookType(final String notebookType) {
    this.notebookType = notebookType;
  }

  /**
   * A Uri scheme, like `file` or `untitled`.
   */
  public String getScheme() {
    return this.scheme;
  }

  /**
   * A Uri scheme, like `file` or `untitled`.
   */
  public void setScheme(final String scheme) {
    this.scheme = scheme;
  }

  /**
   * A glob pattern.
   */
  public String getPattern() {
    return this.pattern;
  }

  /**
   * A glob pattern.
   */
  public void setPattern(final String pattern) {
    this.pattern = pattern;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("notebookType", this.notebookType);
    b.add("scheme", this.scheme);
    b.add("pattern", this.pattern);
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
    NotebookDocumentFilter other = (NotebookDocumentFilter) obj;
    if (this.notebookType == null) {
      if (other.notebookType != null)
        return false;
    } else if (!this.notebookType.equals(other.notebookType))
      return false;
    if (this.scheme == null) {
      if (other.scheme != null)
        return false;
    } else if (!this.scheme.equals(other.scheme))
      return false;
    if (this.pattern == null) {
      if (other.pattern != null)
        return false;
    } else if (!this.pattern.equals(other.pattern))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.notebookType== null) ? 0 : this.notebookType.hashCode());
    result = prime * result + ((this.scheme== null) ? 0 : this.scheme.hashCode());
    return prime * result + ((this.pattern== null) ? 0 : this.pattern.hashCode());
  }
}
