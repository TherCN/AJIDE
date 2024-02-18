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
 * Changes to cells
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentChangeEventCells {
  /**
   * Changes to the cell structure to add or
   * remove cells.
   */
  private NotebookDocumentChangeEventCellStructure structure;

  /**
   * Changes to notebook cells properties like its
   * kind, execution summary or metadata.
   */
  private List<NotebookCell> data;

  /**
   * Changes to the text content of notebook cells.
   */
  private List<NotebookDocumentChangeEventCellTextContent> textContent;

  public NotebookDocumentChangeEventCells() {
  }

  /**
   * Changes to the cell structure to add or
   * remove cells.
   */
  public NotebookDocumentChangeEventCellStructure getStructure() {
    return this.structure;
  }

  /**
   * Changes to the cell structure to add or
   * remove cells.
   */
  public void setStructure(final NotebookDocumentChangeEventCellStructure structure) {
    this.structure = structure;
  }

  /**
   * Changes to notebook cells properties like its
   * kind, execution summary or metadata.
   */
  public List<NotebookCell> getData() {
    return this.data;
  }

  /**
   * Changes to notebook cells properties like its
   * kind, execution summary or metadata.
   */
  public void setData(final List<NotebookCell> data) {
    this.data = data;
  }

  /**
   * Changes to the text content of notebook cells.
   */
  public List<NotebookDocumentChangeEventCellTextContent> getTextContent() {
    return this.textContent;
  }

  /**
   * Changes to the text content of notebook cells.
   */
  public void setTextContent(final List<NotebookDocumentChangeEventCellTextContent> textContent) {
    this.textContent = textContent;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("structure", this.structure);
    b.add("data", this.data);
    b.add("textContent", this.textContent);
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
    NotebookDocumentChangeEventCells other = (NotebookDocumentChangeEventCells) obj;
    if (this.structure == null) {
      if (other.structure != null)
        return false;
    } else if (!this.structure.equals(other.structure))
      return false;
    if (this.data == null) {
      if (other.data != null)
        return false;
    } else if (!this.data.equals(other.data))
      return false;
    if (this.textContent == null) {
      if (other.textContent != null)
        return false;
    } else if (!this.textContent.equals(other.textContent))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.structure== null) ? 0 : this.structure.hashCode());
    result = prime * result + ((this.data== null) ? 0 : this.data.hashCode());
    return prime * result + ((this.textContent== null) ? 0 : this.textContent.hashCode());
  }
}
