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

import com.google.gson.annotations.JsonAdapter;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A change event for a notebook document.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentChangeEvent {
  /**
   * The changed meta data if any.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object metadata;

  /**
   * Changes to cells
   */
  private NotebookDocumentChangeEventCells cells;

  public NotebookDocumentChangeEvent() {
  }

  /**
   * The changed meta data if any.
   */
  public Object getMetadata() {
    return this.metadata;
  }

  /**
   * The changed meta data if any.
   */
  public void setMetadata(final Object metadata) {
    this.metadata = metadata;
  }

  /**
   * Changes to cells
   */
  public NotebookDocumentChangeEventCells getCells() {
    return this.cells;
  }

  /**
   * Changes to cells
   */
  public void setCells(final NotebookDocumentChangeEventCells cells) {
    this.cells = cells;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("metadata", this.metadata);
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
    NotebookDocumentChangeEvent other = (NotebookDocumentChangeEvent) obj;
    if (this.metadata == null) {
      if (other.metadata != null)
        return false;
    } else if (!this.metadata.equals(other.metadata))
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
    result = prime * result + ((this.metadata== null) ? 0 : this.metadata.hashCode());
    return prime * result + ((this.cells== null) ? 0 : this.cells.hashCode());
  }
}
