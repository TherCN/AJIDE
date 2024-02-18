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

/**
 * The document diagnostic report kinds.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public final class DocumentDiagnosticReportKind {
  /**
   * A diagnostic report with a full
   * set of problems.
   */
  public static final String Full = "full";

  /**
   * A report indicating that the last
   * returned report is still accurate.
   */
  public static final String Unchanged = "unchanged";

  private DocumentDiagnosticReportKind() {
  }
}
