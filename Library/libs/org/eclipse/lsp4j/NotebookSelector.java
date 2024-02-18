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
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The notebooks to be synced.
 * <p>
 * At least one of either {@link #notebook} or {@link #cells} is required.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookSelector {
  /**
   * The notebook to be synced. If a string
   * value is provided it matches against the
   * notebook type. '*' matches every notebook.
   */
  private Either<String, NotebookDocumentFilter> notebook;

  /**
   * The cells of the matching notebook to be synced.
   */
  private List<NotebookSelectorCell> cells;

  public NotebookSelector() {
  }

  /**
   * The notebook to be synced. If a string
   * value is provided it matches against the
   * notebook type. '*' matches every notebook.
   */
  public Either<String, NotebookDocumentFilter> getNotebook() {
    return this.notebook;
  }

  /**
   * The notebook to be synced. If a string
   * value is provided it matches against the
   * notebook type. '*' matches every notebook.
   */
  public void setNotebook(final Either<String, NotebookDocumentFilter> notebook) {
    this.notebook = notebook;
  }

  public void setNotebook(final String notebook) {
    if (notebook == null) {
      this.notebook = null;
      return;
    }
    this.notebook = Either.forLeft(notebook);
  }

  public void setNotebook(final NotebookDocumentFilter notebook) {
    if (notebook == null) {
      this.notebook = null;
      return;
    }
    this.notebook = Either.forRight(notebook);
  }

  /**
   * The cells of the matching notebook to be synced.
   */
  public List<NotebookSelectorCell> getCells() {
    return this.cells;
  }

  /**
   * The cells of the matching notebook to be synced.
   */
  public void setCells(final List<NotebookSelectorCell> cells) {
    this.cells = cells;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("notebook", this.notebook);
    b.add("cells", this.cells);
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
    NotebookSelector other = (NotebookSelector) obj;
    if (this.notebook == null) {
      if (other.notebook != null)
        return false;
    } else if (!this.notebook.equals(other.notebook))
      return false;
    if (this.cells == null) {
      if (other.cells != null)
        return false;
    } else if (!this.cells.equals(other.cells))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.notebook== null) ? 0 : this.notebook.hashCode());
    return prime * result + ((this.cells== null) ? 0 : this.cells.hashCode());
  }
}
